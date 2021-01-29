# MCUtils
This .java class are for make plugins easily

# Utilities

In Main Class put this code:

        new BukkitRunnable() {
            @Override
            public void run() {
                BungeeCord.refreshGlobalCount();
                BungeeCord.refreshServerList();
                BungeeCord.refreshServerCount();
            }
        }.runTaskTimer(this, 0, 10L);

To get a server is: 
        **BungeeCord.getServersByName().get("name-server").getPlayerCount()**
        
To get global players is:
        **BungeeCord.getGlobalPlayers();**
