#!/bin/sh
set -e

# Vault address and token
VAULT_ADDR="http://vault:8200"
VAULT_TOKEN="root"

echo "⏳ Waiting for Vault to be ready..."

# Wait until Vault is initialized and ready
until wget -qO- http://vault:8200/v1/sys/health | grep '"initialized":true' > /dev/null; do
  echo "❌ Vault is not ready yet, waiting..."
  sleep 2
done

echo "✅ Vault is ready!"

# Export Vault environment variables for CLI
export VAULT_ADDR=$VAULT_ADDR
export VAULT_TOKEN=$VAULT_TOKEN

# Add secrets to Vault
echo "🔐 Adding secrets to Vault..."

vault kv put secret/order-service db.username=user db.password=pass
vault kv put secret/product-service db.username=user db.password=pass
vault kv put secret/notification-service db.username=user db.password=pass

vault kv put secret/rabbitmq username=guest password=guest

echo "🎉 Secrets have been successfully set!"
