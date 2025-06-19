
## 🔑 **How It Works**

1️⃣ **Login**

```http
POST /api/authenticate
Content-Type: application/json

{
  "username": "admin",
  "password": "admin12345"
}

✅ Response:
{
  "username": "admin",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
