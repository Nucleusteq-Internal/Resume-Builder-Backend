locals {
  account_name   = "portal_prod_account"
  aws_account_id = "547568624982"
  aws_region     = "us-west-2"
  app_name       = "resume-builder-backend-api"
  eks_cluster_name = "portal_eks_cluster"
  tags = {
    environment = "prod"
    project = "nt-enterprise-apps"
  }
  enterprise_website_subdomain_resume_builder="resume"
  enterprise_website_domain_name="nucleusteq.com"
}