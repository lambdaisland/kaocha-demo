(ns figwheel-repl.test
  (:require [figwheel.repl :as fr]))

(defn repl-env [& {:as opts}]
  (fr/repl-env* opts))
