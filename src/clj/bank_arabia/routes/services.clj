(ns bank-arabia.routes.services
  (:require [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [ring.util.http-response :as response]
            [bank-arabia.db.core :as db]
            [bank-arabia.auth :as auth]
            ;[bank-arabia.middleware.formats :as formats]
            [bank-arabia.middleware :as middleware]))

(defn service-routes []
  ["/api" {:middleware [middleware/wrap-formats]
           :swagger {:id ::api}}
           ;middleware formats/instance
   ["" {:no-doc true}
    ["/swagger.json"
     {:get (swagger/create-swagger-handler)}]
    ["/swagger-ui*"
     {:get (swagger-ui/create-swagger-ui-handler
            {:url "/api/swagger.json"})}]]
   ["/login" {:post
              {:parameters
               {:body {:username string?
                       :password string?}}}
              :responses {200 {:body map?}
                          401 {:body {:message string?}}}
              :handler (fn [{{{:keys [username password]} :body}
                            :paramters}]
                         )}]
   ])

