;; build.boot
(set-env! :source-paths #{"src"}
          :dependencies '[[lambdaisland/kaocha-boot "0.0-5"]
                          [lambdaisland/kaocha-cucumber "RELEASE"]])

(require '[kaocha.boot-task :refer [kaocha]])
