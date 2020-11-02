# Ktor FCM Push Service

### Usage

#### run the service

`./gradlew run`

---

#### `POST /data`

Sends a data push to all devices

Query params: `test={boolean}` (Optional)

set to `true` to send only to test environments, can be omitted to use production value

Body: `data` object to send

Example:
```
curl -X POST \
"127.0.0.1:8181/data?test=true" \
-H 'Content-Type: application/json' \
-d '{"data": { "name" : "test" } }'
```
---

#### `POST /push`

Sends a simple notification push to all devices

* Query params: `test={boolean}` (Optional)

set to `true` to send only to test environments, can be omitted to use production value

* Body: `Notification`

Example:
```
curl -X POST \
"127.0.0.1:8181/push?test=true" \
-H 'Content-Type: application/json' \
-d '{"notification": { "title" : "test", "body": "test" } }'
```
---

### Requirements

* gcloud user able to use FCM with your Project in `firebase-cloud-messenger.json`

* `FCMConfig` in `fcm_app_config.json`

all values are optional

Example:
```
{
  "android": {
    "test": "{{android_test_projectid}}",
    "production": "{{android_production_projectid}}"
  },
  "ios": {
    "test": "{{ios_test_projectid}}",
    "production": "{{ios_production_projectid}}"
  }
}
```
