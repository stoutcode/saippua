resource "aws_s3_bucket" "this" {
  bucket = var.solutions_bucket_name
  acl    = "public-read"

  lifecycle {
    prevent_destroy = false
  }
}

resource "aws_s3_bucket_object" "this" {
  bucket = aws_s3_bucket.this.id
  key    = var.war_app_specs.filename
  source = "${var.solutions_dir}/${var.war_app_specs.filename}"
}

resource "aws_elastic_beanstalk_application" "this" {
  name = var.war_app_specs.name
}

resource "aws_elastic_beanstalk_application_version" "this" {
  application = aws_elastic_beanstalk_application.this.name
  name        = "1.0"
  bucket      = aws_s3_bucket.this.id
  key         = aws_s3_bucket_object.this.id
}

resource "aws_elastic_beanstalk_environment" "this" {
  name                = var.war_app_specs.env_name
  application         = aws_elastic_beanstalk_application.this.name
  version_label       = aws_elastic_beanstalk_application_version.this.name
  solution_stack_name = "64bit Amazon Linux 2018.03 v3.4.11 running Tomcat 8.5 Java 8"
  cname_prefix        = "ties4560"

  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name = "IamInstanceProfile"
      value = "aws-elasticbeanstalk-ec2-role"
  }

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "1"
  }
  # bunch of other beanstalk settings
}
