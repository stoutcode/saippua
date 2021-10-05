terraform {
  backend "s3" {
    bucket = "ties4560-tfstate-bucket"
    key    = "ties4560.tfstate"
    region = "eu-north-1"
  }
}
