variable "region" {
  description = "Region to deploy into."
  type        = string
}

variable "solutions_bucket_name" {
  description = "Name of the bucket where previous code is uploaded to."
  type        = string
}

variable "solutions_dir" {
  description = "Local directory for solutions."
  type        = string
  default     = "solutions"
}

variable "war_app_specs" {
  description = "WAR application specs on AWS."
  type        = list(map(string))
  default = [
    {
      name           = "restApi",
      env_name       = "restEnvironment",
      filename       = "rest.war",
      cname_prefix   = "ties4560",
      solution_stack = "64bit Amazon Linux 2 v4.2.6 running Tomcat 8.5 Corretto 11"
    },
  ]
}

variable "extra_setting" {
  default = [
    [
      null,
      {
        namespace = "aws:elasticbeanstalk:application:environment"
        name      = "SERVER_PORT"
        value     = "8080"
      }
    ]
  ]
}

variable "version_number" {
  description = "Specify a new version number to update EB envs"
  type        = string
  default     = "1.4"
}