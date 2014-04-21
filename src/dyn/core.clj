(ns dyn.core)
(use 'lamina.core 'aleph.http 'aleph.formats)

(def broadcast-channel (channel))

(defn chat-handler [ch handshake]
  (receive-all ch (fn [msg] (enqueue broadcast-channel msg)))
  (receive-all broadcast-channel (fn [msg] (enqueue ch msg))))

(defn -main [& args]
  (start-http-server chat-handler {:port 8081 :websocket true})
  )


