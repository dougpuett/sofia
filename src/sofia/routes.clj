(ns sofia.routes
    (:refer-clojure :exclude [find])
    (:require (compojure handler route core response)
        [cheshire.core :refer :all]
        [compojure.core :refer :all]
        [compojure.response :refer :all]
        [compojure.handler :refer :all]
        [ring.adapter.jetty :only (run-jetty)]
        [ring.util.response :as response]
        [ring.middleware.params :refer :all]
        ; [taoensso.carmine :as car :refer (wcar)]
        ; [sofia.password_admin :refer :all]
        ; [cemerick.friend :as friend] (cemerick.friend [workflows :as workflows] [credentials :as creds]))
    )

;; =================
;; FILE DESCRIPTION:
;; =================

;; THIS FILE MAPS ROUTES TO FUNCTIONS. EACH ROUTE CALLS A FUNCTION THAT EITHER RETURNS HTML OR RAW DATA IN JSON FORM. IT ALSO CONTAINS A RING WRAPPER THAT TAKES CARE OF AUTHENTICATION BY CALLING A REDIS SERVER.

(defroutes router*
    ;; ==========
    ;; SITE INDEX
    ;; ==========

    (GET "/" [] "Hello, World")
    ; (GET "/login" [] login-form)
    ; (GET "/logout" request (friend/logout* (response/redirect (str (:context request) "/"))))
    (compojure.route/resources "/")
    (compojure.route/not-found "Link not found."))

;; ===============
;; AUTHENTICATION:
;; ===============

; (def secured-app (friend/authenticate
;     router*
;         {:allow-anon? true
;         :login-uri "/login"
;         :default-landing-uri "/"
;         :unauthorized-handler #(-> (str "You do not have sufficient privileges to access" (:uri %))
;             response/response
;             (response/status 401))
;         :credential-fn redis-credentials
;         :workflows [(workflows/interactive-form)]}))

; (def router (site secured-app))
(def router router*)