(ns bank-arabia.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [bank-arabia.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[bank_arabia started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[bank_arabia has shut down successfully]=-"))
   :middleware wrap-dev})
