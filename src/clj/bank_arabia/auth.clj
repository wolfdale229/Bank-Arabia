;; Making the user creation and authentication functionality

(ns bank-arabia.auth
  (:require [buddy.hashers :as hashers]
            [bank-arabia.db.core :as db]))

;; create the user registration function
;; the function takes two arguments, username and a password
;; if the username doesn't exist, create a new user
;; hashing the provided password
(defn create-user! [username password]
  (if-not (empty? (db/get-customer-for-auth! {:username username}))
    (throw (ex-info "User already exists!"
                    {:bank-arabia/user-exist-error-id ::duplicate
                     :error "User already exists!"}))
    (db/create-user! {:username username
                         :password (hashers/derive password)})))

;; create the user login/authentication function
;; The function takes two arguments, a username and user's password
;; If the username is in the database, check if the given password
;; matches.

(defn authenticate-user! [username password]
  (let [{hashed :password :as user} (db/get-customer-for-auth!
                                     {:username username})]
    (if (hashers/check password hashed)
      (dissoc user :password)
      (throw (ex-info "Username or password incorrect"
                      {:bank-arabia/auth-error-id ::unauthorized
                       :error "Username or password incorrect"})))))
