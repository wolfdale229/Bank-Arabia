(ns bank-arabia.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[bank_arabia started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[bank_arabia has shut down successfully]=-"))
   :middleware identity})
