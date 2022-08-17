;; Making the user creation and authentication functionality

(ns bank-arabia.auth
  (:require [buddy.hashers :as hashers]
            [bank-arabia.db.core :as db]))

;; create the registration functionality
(defn create-user! [username password]
  (if-not (empty? (db/get-customer-for-auth! {:username username}))
    (throw (ex-info "User exists already!"
                    {:bank-arabia/user-error-id ::duplicate
                     :error "User already exists!"}))
    (db/create-customer {:username username
                         :password (hashers/derive password)})))

(defn authenticate-user [username password]
  (let [{hashed :password :as user} (db/get-customer-for-auth! {:username username})]
    (when (hashers/check password hashed)
      (dissoc user :password))))
