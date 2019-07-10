(ns watch-test
  (:require [kaocha.repl :as kao-repl]
            [kaocha.watch :as kao-watch]
            [cljs.core.server :as cljs-server]
            [clojure.java.io :as io]
            [clojure.tools.reader.reader-types :as ctr.types]
            [kaocha.cljs.prepl :as kcr]
            [cljs.repl.node])
  (:import java.util.concurrent.LinkedBlockingQueue))

(def x (kao-watch/run (kao-repl/config)))

;;


(def results (atom []))

(defn handle-out [out]
  (clojure.pprint/pprint out)
  (swap! results conj out))

(def writable-reader
  (kcr/writable-reader))

(def prepl-future
  (let [repl-env        (cljs.repl.node/repl-env)
        opts            {}
        reader          (ctr.types/source-logging-push-back-reader (ctr.types/push-back-reader writable-reader))]
    (future
      (cljs-server/prepl repl-env opts reader handle-out))))

(kcr/write writable-reader "(+ 1 1)\n")


;;;;;;;;;;;;;;;;;;;;;;;;;

(let [chan (LinkedBlockingQueue.)
      eval (kcr/prepl (cljs.repl.node/repl-env) chan)]
  (def eval-cljs eval)
  (def res-chan chan))

(eval-cljs "(+ 1 1)")

(def results
  (take-while identity (repeatedly #(.poll res-chan))))

(count results)

(sc.api/letsc 1
              in-reader)

(count
 (take-while identity (repeatedly #(.poll res-chan))))
