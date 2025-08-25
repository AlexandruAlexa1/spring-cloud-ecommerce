#!/bin/sh

echo "⏳ Waiting for config-server at $CONFIG_SERVER_URL..."
until wget -qO- http://config-server:8888/actuator/health | grep '"status":"UP"' > /dev/null; do
  echo "❌ config-server is not ready yet, waiting..."
  sleep 2
done
echo "✅ config-server is UP."

echo "🚀 Starting the application..."
exec java -jar /app/app.jar
