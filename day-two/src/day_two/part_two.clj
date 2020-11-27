(ns day-two.part-two
  (:require [clojure.string :as str]
            [day-two.lib :refer [ingest]]))

(defn- valid? [{:keys [policy password]}]
  (let [{:keys [rng letter]} policy
        [fst snd] (map (partial get password) (map dec rng))
        fst-is-letter (= fst letter)
        snd-is-letter (= snd letter)]
    (and 
      (or fst-is-letter snd-is-letter) 
      (not (and fst-is-letter snd-is-letter)))))

(defn solve [input-filepath]
  (->> (ingest input-filepath) (filter valid?) count))


