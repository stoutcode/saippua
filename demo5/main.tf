resource "aws_s3_bucket" "this" {
  bucket = var.solutions_bucket_name
  acl    = "public-read"

  lifecycle {
    prevent_destroy = false
  }
}

resource "aws_s3_bucket_object" "this" {
  count = length(var.war_app_specs)

  bucket = aws_s3_bucket.this.id
  key    = var.war_app_specs[count.index].name
  source = "${var.solutions_dir}/${var.war_app_specs[count.index].filename}"
}

resource "aws_elastic_beanstalk_application_version" "this" {
  count = length(var.war_app_specs)

  application = var.war_app_specs[count.index].id
  name        = "1.0"
  bucket      = aws_s3_bucket.this.id
  key         = var.war_app_specs[count.index].name
}

resource "aws_elastic_beanstalk_environment" "this" {
  count = length(var.war_app_specs)

  name                = var.war_app_specs[count.index].name
  application         = var.war_app_specs[count.index].id
  solution_stack_name = "64bit Amazon Linux 2017.03 v2.6.2 running Tomcat 8 Java 8"
  version_label       = aws_elastic_beanstalk_application_version.this[count.index].name

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "1"
  }
  # bunch of other beanstalk settings
}
