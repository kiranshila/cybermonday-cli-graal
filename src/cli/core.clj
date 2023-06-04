(ns cli.core
  (:require
   [clojure.tools.cli :refer [parse-opts]]
   [cybermonday.core :as cm]
   [clojure.java.io :as io])
  (:import (java.io BufferedReader))
  (:gen-class))

(set! *warn-on-reflection* true)

(def cli-options
  [["-f" "--uri URI" "A markdown URI (local file or website)"]
   ["-s" "--stdin" "Read from stdin"]])

(defn -main [& args]
  (let [{:keys [uri stdin]} (:options (parse-opts args cli-options))]
    (when (and (not (nil? uri)) stdin)
      (println "Reading from a file and stdin are mutually exclusive")
      (System/exit -1))
    (prn (with-open [^BufferedReader rdr (if stdin (java.io.BufferedReader. *in*) (io/reader uri))]
           (cm/parse-md rdr)))))
