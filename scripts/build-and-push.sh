#!/bin/bash

# =========================
# Configuration
# =========================
DOCKER_USER="xalx1"                 # Your Docker Hub username
VERSION="1.0.0-compose"             # Image tag/version
SERVICES=("config-server" "discovery-server" "gateway-server" "order-service" "product-service" "notification-service")

# =========================
# Build & Push
# =========================
for SERVICE in "${SERVICES[@]}"; do
    IMAGE_NAME="$DOCKER_USER/$SERVICE:$VERSION"

    echo "🚀 Building image for $SERVICE..."
    docker build -t "$IMAGE_NAME" "../$SERVICE"

    if [ $? -ne 0 ]; then
        echo "❌ Build failed for $SERVICE. Stopping script."
        exit 1
    fi

    echo "📤 Pushing image $IMAGE_NAME to Docker Hub..."
    docker push "$IMAGE_NAME"

    if [ $? -ne 0 ]; then
        echo "❌ Push failed for $SERVICE. Stopping script."
        exit 1
    fi

    echo "✅ $SERVICE published successfully!"
    echo "----------------------------------"
done

echo "🎉 All services built and pushed successfully!"
