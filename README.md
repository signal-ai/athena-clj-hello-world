# athena-clj-hello-world

Prepare env. vars:

```
export AWS_ACCESS_KEY_ID=
export AWS_SECRET_ACCESS_KEY=
export ATHENA_S3_STAGING_DIR=
```

Run in `lein repl`:
```clojure
(require '[athena.core :as a] :reload-all)
(a/query "SELECT * FROM table WHERE field = true LIMIT 10;")
```
