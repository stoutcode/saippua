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
  type        = map(string)
  default = {
    name     = "myApplication",
    env_name = "myEnvironment",
    filename = "rest.war"
  }
}
