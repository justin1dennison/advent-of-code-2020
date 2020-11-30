(ns day-four.core
  (:require [clojure.string :as str]
            [clojure.set :as set])
  (:gen-class))

;; constants
(def input-filepath "./resources/input.txt")
(def required-fields #{:byr :iyr :eyr :hgt :hcl :ecl :pid})
(def optional-fields #{:cid})
(def record-separator #"\n\n")
(def field-separator #"[\n\s]")
(def attr-separator #":")
(def eye-colors #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})

;; helpers 
(defn- digit? [n] (Character/isDigit n))
(def ^:private all (partial every? identity))
;; parsing
(defn split [pattern s] (str/split s pattern))

(defn length-with-unit [s]
  (let [value (apply str (take-while digit? s))
        unit  (apply str (drop (count value) s))]
    {:value (if value (Integer/parseInt value))
     :unit (if unit (keyword unit))}))

(def transforms {:byr #(Integer/parseInt %)
                 :iyr #(Integer/parseInt %)
                 :eyr #(Integer/parseInt %)
                 :hgt length-with-unit})


(defn parse-passport [record]
  (->> record
       (split field-separator)
       (map (partial split attr-separator))
       (map (fn [[k v]] [(keyword k) ((get transforms (keyword k) identity) v)]))
       (into {})))

;; validations
(defn valid-height? [{:keys [unit value]}]
  (case unit
    :in (and (>= value 59) (<= value 76))
    :cm (and (>= value 150) (<= value 193))
    false))

(def validators  {:byr #(and (<= % 2002) (>= % 1920))
                  :iyr #(and (<= % 2020) (>= % 2010))
                  :eyr #(and (<= % 2030) (>= % 2020))
                  :hgt valid-height?
                  :hcl #(re-matches #"#[a-f0-9]{6}" %)
                  :ecl #(contains? eye-colors %)
                  :pid #(re-matches #"\d{9}" %)})

(defn simple-valid-passport? [passport]
  (let [fields (set (keys passport))]
    (set/superset? fields required-fields)))

(defn valid-passport? [passport]
  (let [v #((get validators % identity) (% passport))
        fields (keys passport)]
    (and
     (simple-valid-passport? passport)
     (all (map v fields)))))

(defn solve [validator input]
  (->> input
       (split record-separator)
       (map parse-passport)
       (filter validator)
       count))

(defn -main [& args]
  (println {:part-one (->> input-filepath slurp (solve simple-valid-passport?))
            :part-two (->> input-filepath slurp (solve valid-passport?))}))

