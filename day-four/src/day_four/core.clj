(ns day-four.core
  (:require [clojure.string :as str]
            [clojure.walk :as w]
            [clojure.set :as set])
  (:gen-class))

(def input-filepath "./resources/input.txt")
(def required-fields #{:byr :iyr :eyr :hgt :hcl :ecl :pid})
(def optional-fields #{:cid})
(def record-separator #"\n\n")
(def field-separator #"[\n\s]")
(def attr-separator #":")
(defn split [pattern s] (str/split s pattern))
(defn parse-passport [record]
  (->> record
       (split field-separator)
       (map (partial split attr-separator))
       (map (fn [[k v]] [(keyword k) v]))
       (into {})))

(defn valid-passport? [passport]
  (let [fields (set (keys passport))]
    (set/superset? fields required-fields)))

(defn solve [input]
  (->> input
       (split record-separator)
       (map parse-passport)
       (filter valid-passport?)
       count))

(defn -main [& args]
  (println {:part-one (->> input-filepath slurp solve)}))

;;playground
