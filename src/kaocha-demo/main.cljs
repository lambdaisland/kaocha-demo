(ns kaocha-demo.main
  (:require [figwheel.repl :as repl]))

(repl/connect  "ws://localhost:9500/figwheel-connect")
