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
      name     = "JettyApplication",
      id       = "myJettyApp",
      filename = "jetty.war"
    },
    {
      name     = "SoapApi",
      id       = "mySoapApi",
      filename = "soap.war"
    },
    {
      name     = "RestApi",
      id       = "myRestApi",
      filename = "rest.war"
    }
  ]
}
