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
  key    = var.war_app_specs[count.index].filename
  source = "${var.solutions_dir}/${var.war_app_specs[count.index].filename}"
  etag   = filemd5("${var.solutions_dir}/${var.war_app_specs[count.index].filename}")
}

resource "aws_elastic_beanstalk_application" "this" {
  count = length(var.war_app_specs)

  name = var.war_app_specs[count.index].name
}

resource "aws_elastic_beanstalk_application_version" "this" {
  count = length(var.war_app_specs)

  application = aws_elastic_beanstalk_application.this[count.index].name
  name        = var.version_number
  bucket      = aws_s3_bucket.this.id
  key         = aws_s3_bucket_object.this[count.index].id
}

resource "aws_elastic_beanstalk_environment" "this" {
  count = length(var.war_app_specs)

  name                = var.war_app_specs[count.index].env_name
  application         = aws_elastic_beanstalk_application.this[count.index].name
  version_label       = aws_elastic_beanstalk_application_version.this[count.index].name
  solution_stack_name = var.war_app_specs[count.index].solution_stack
  cname_prefix        = var.war_app_specs[count.index].cname_prefix

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "aws-elasticbeanstalk-ec2-role"
  }

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "1"
  }

  #setting {
  #  namespace = "aws:elasticbeanstalk:application:environment"
  #  name      = "SERVER_PORT"
  #  value     = "8080"
  #}
}
