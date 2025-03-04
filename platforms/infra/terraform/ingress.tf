# data "aws_route53_zone" "website_hosted_zone" {
#   name         = var.enterprise_website_domain_name
#   private_zone = false
# }

# resource "aws_route53_zone" "resume_builder_hosted_zone" {
#   name = "${var.enterprise_website_subdomain_resume_builder}.${var.enterprise_website_domain_name}"
# }

# resource "aws_route53_record" "website-ns-record-resume-builder-hosted-zone" {
#   zone_id = data.aws_route53_zone.website_hosted_zone.zone_id
#   name    = "${var.enterprise_website_subdomain_resume_builder}.${var.enterprise_website_domain_name}"
#   type    = "NS"
#   records = aws_route53_zone.resume_builder_hosted_zone.name_servers
#   ttl     = 300
# }

# data "aws_elb_hosted_zone_id" "elb_zone_id" {}

# resource "aws_route53_record" "resume-builder-aname-record" {
#   zone_id = aws_route53_zone.resume_builder_hosted_zone.zone_id
#   name    = "${var.enterprise_website_subdomain_resume_builder}.${var.enterprise_website_domain_name}"
#   type    = "A"
#   alias {
#     name                   = kubernetes_ingress_v1.resume_builder_ingress.status.0.load_balancer.0.ingress.0.hostname
#     zone_id                = data.aws_elb_hosted_zone_id.elb_zone_id.id
#     evaluate_target_health = true
#   }
# }

# resource "aws_acm_certificate" "resume_builder_ssl_certificate" {
#   domain_name       = "${var.enterprise_website_subdomain_resume_builder}.${var.enterprise_website_domain_name}"
#   validation_method = "DNS"

#   tags = {
#     project = "${var.enterprise_website_subdomain_resume_builder}-cert"
#   }

#   lifecycle {
#     create_before_destroy = true
#   }
# }

# resource "aws_route53_record" "cert-cname" {
#   for_each = {
#     for dvo in aws_acm_certificate.resume_builder_ssl_certificate.domain_validation_options : dvo.domain_name => {
#       name   = dvo.resource_record_name
#       record = dvo.resource_record_value
#       type   = dvo.resource_record_type
#     }
#   }

#   allow_overwrite = true
#   name            = each.value.name
#   records         = [each.value.record]
#   ttl             = 60
#   type            = each.value.type
#   zone_id         = aws_route53_zone.resume_builder_hosted_zone.zone_id
# }

# resource "aws_route53_record" "attach-cname-record" {
#   zone_id = aws_route53_zone.resume_builder_hosted_zone.zone_id
#   name    = "www.${var.enterprise_website_subdomain_resume_builder}.${var.enterprise_website_domain_name}"
#   type    = "CNAME"
#   ttl     = "300"
#   records = [kubernetes_ingress_v1.resume_builder_ingress.status.0.load_balancer.0.ingress.0.hostname]
# }

# resource "aws_acm_certificate_validation" "cert_validate" {
#   certificate_arn         = aws_acm_certificate.resume_builder_hosted_zone.arn
#   validation_record_fqdns = [for record in aws_route53_record.cert-cname : record.fqdn]
# }

# resource "kubernetes_ingress_v1" "resume_builder_ingress" {
#   wait_for_load_balancer = true
#   metadata {
#     name      = "resume-builder-ingress"
#     namespace = "plasma-namespace"
#     annotations = {
#       "alb.ingress.kubernetes.io/load-balancer-name"           = "resume-builder-${var.tags.project}-alb"
#       "alb.ingress.kubernetes.io/scheme"                       = "internet-facing"
#       "alb.ingress.kubernetes.io/target-type"                  = "ip"
#       "alb.ingress.kubernetes.io/ssl-redirect"                 = "443"
#       "alb.ingress.kubernetes.io/listen-ports"                 = "[{\"HTTPS\":443}, {\"HTTP\":80}]"
#       "alb.ingress.kubernetes.io/healthcheck-protocol"         = "HTTP"
#       "alb.ingress.kubernetes.io/healthcheck-port"             = "traffic-port"
#       "alb.ingress.kubernetes.io/healthcheck-interval-seconds" = "15"
#       "alb.ingress.kubernetes.io/healthcheck-timeout-seconds"  = "5"
#       "alb.ingress.kubernetes.io/success-codes"                = "200,404,301,302"
#       "alb.ingress.kubernetes.io/healthy-threshold-count"      = "2"
#       "alb.ingress.kubernetes.io/unhealthy-threshold-count"    = "4"
#       "alb.ingress.kubernetes.io/certificate-arn"              = aws_acm_certificate.resume_builder_ssl_certificate.arn
#       "alb.ingress.kubernetes.io/actions.ssl-redirect"         = "{\"Type\": \"redirect\", \"RedirectConfig\": { \"Protocol\": \"HTTPS\", \"Port\": \"443\", \"StatusCode\": \"HTTP_301\"}}"
#     }
#   }
#   spec {
#     ingress_class_name = "alb"
#     rule {
#       http {
#         path {
#           path = "/token/v1/plasma/*"
#           backend {
#             service {
#               name = "plasma-token-api-svc"
#               port {
#                 number = 8080
#               }
#             }
#           }
#         }
#       }
#     }
#     tls {
#       secret_name = "tls-secret"
#     }
#   }
# }
