(ns athena.core
  (:require [environ.core :refer [env]])
  (:import [java.sql DriverManager SQLException]
            java.util.Properties))

(def ^:dynamic *connection-uri* "jdbc:awsathena://athena.us-east-1.amazonaws.com:443/")

(defn get-connection []
  (let [conn-settings (Properties.)]
    (try (.put conn-settings "user" (env :aws-access-key-id)) (catch Exception e false))
    (try (.put conn-settings "password" (env :aws-secret-access-key)) (catch Exception e false))
    (try (.put conn-settings "s3_staging_dir" (env :athena-s3-staging-dir)) (catch Exception e false))
    (DriverManager/getConnection *connection-uri* conn-settings)))

(defn get-row-data [result-set]
  (loop [i 1
         result []]
    (if-let [column (try (.getString result-set i) (catch SQLException e nil))]
      (recur (inc i) (conj result column))
      result)))

(defn result-set->vector [result-set]
  (loop [result []]
    (if-let [next-result (try (.next result-set) (catch SQLException e nil))]
      (recur (conj result (get-row-data result-set)))
      result)))

(defn read-query [query]
  (-> (get-connection)
      .createStatement
      (.executeQuery query)
      result-set->vector))

(defn write-query [query]
  (-> (get-connection)
      .createStatement
      (.executeUpdate query)))
