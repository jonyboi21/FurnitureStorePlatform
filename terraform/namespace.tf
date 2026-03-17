resource "kubernetes_namespace" "furniture_store" {
  metadata {
    name = "furniture-store"
  }
}