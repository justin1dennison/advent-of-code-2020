(ns day-two.part-one
  (:require [clojure.string :as str]
            [day-two.lib :refer [ingest]]))

(defn- valid? [{:keys [policy password]}]
  (let [{:keys [rng letter]} policy
        [lower upper] rng
        letter-freq (frequencies password)
        cnt (get letter-freq letter 0)]
    (and (<= cnt upper) (>= cnt lower))))

(defn solve [input-filepath] (->> (ingest input-filepath) (filter valid?) count))

