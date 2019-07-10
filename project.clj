(defproject kaocha-demo "0.0"




  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "0.0-367"]
                                     [lambdaisland/kaocha-cucumber "RELEASE"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}

  )
