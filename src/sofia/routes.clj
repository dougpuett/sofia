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
        [clojure.java.io :as io]
        [clojure.string]
        ;; [taoensso.carmine :as car :refer (wcar)]
        ;; [sofia.password_admin :refer :all]
        ;; [cemerick.friend :as friend] (cemerick.friend [workflows :as workflows] [credentials :as creds]))
    )
)

;; =================
;; FILE DESCRIPTION:
;; =================

;; THIS FILE MAPS ROUTES TO FUNCTIONS. EACH ROUTE CALLS A FUNCTION THAT EITHER RETURNS HTML OR RAW DATA IN JSON FORM. IT ALSO CONTAINS A RING WRAPPER THAT TAKES CARE OF AUTHENTICATION BY CALLING A REDIS SERVER.

(def all-data (clojure.string/split (slurp (io/file (io/resource "data/temp-data.jsonl"))) #"\n"))

(defn presentation [in-json] (str in-json))


(defroutes router*
    ;; ==========
    ;; SITE INDEX
    ;; ==========

    ; (GET "/" [] (reduce str (map presentation all-data)))
    (GET "/" [] "<body>Welcome to dougpuett.com. This website doesn't do much right now, but may do more someday.</body>")
    (GET "/tasks" [] "<body>Tasks go here</body>")
    (GET "/bookmarks" [] "<body>Bookmarks go here</body>")
    (GET "/concept_map" [] "<body>Concept map visualization goes here</body>")
    (GET "/identity_map" [] "<body>Identity map visualziation goes here</body>")
    ; (GET "/login" [] login-form)
    ; (GET "/logout" request (friend/logout* (response/redirect (str (:context request) "/"))))
    (compojure.route/resources "/")
    (compojure.route/not-found "Link not found."))

;; messaging: email
;; administration: gdocs
;; visualization: web (D3?)
;; how to parse an email: type@.... , subject:name, text: parseable notes

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