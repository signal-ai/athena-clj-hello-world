(defproject athena-clj-hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.1.0"]
                 [com.amazonaws.athena.jdbc/atl-athena-jdbc-driver "1.0.1-atlassian-1"]]
  :main ^:skip-aot athena-clj-hello-world.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repositories {"atlassian-3rdparty" "https://maven.atlassian.com/3rdparty/"})
