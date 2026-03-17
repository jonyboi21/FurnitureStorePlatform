resource "kubernetes_secret_v1" "mongodb" {

  metadata {
    name      = "mongodb-secret"
    namespace = kubernetes_namespace.furniture_store.metadata[0].name
  }

  data = {
    MONGO_URI = var.mongo_uri
  }

  type = "Opaque"

  depends_on = [
    kubernetes_namespace.furniture_store
  ]
}