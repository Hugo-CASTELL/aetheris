<div align="center">
      <img
        src="https://minecraft.wiki/images/Torchflower_JE1_BE1.png"
        width="100"
        height="100"
      />
</div>

<h1 align="center" style="margin-top: 0;">Aetheris</h1>

<p align="center">
    <img alt="1.21.1 Minecraft version" src="https://img.shields.io/badge/1.21.5-20B200?style=for-the-badge&logo=minetest&color=de8b25&logoColor=D9E0EE&labelColor=302D41"/>
    <a href="https://github.com/Hugo-CASTELL/aetheris/releases/latest">
      <img alt="Releases" src="https://img.shields.io/github/v/release/Hugo-CASTELL/aetheris?style=for-the-badge&logo=gitbook&color=26845C&logoColor=D9E0EE&labelColor=302D41"/>
    </a>
    <a href="https://github.com/Hugo-CASTELL/aetheris/stargazers">
      <img alt="Stargazers" src="https://img.shields.io/github/stars/Hugo-CASTELL/aetheris?style=for-the-badge&logo=apachespark&color=805078&logoColor=D9E0EE&labelColor=302D41"/>
    </a>
</p> 

<h2 id="table-of-contents"> :book: Table of Contents</h2>
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#features"> ➤ Features</a></li>
    <li><a href="#about-aetheris"> ➤ About Aetheris</a></li>
    <li><a href="#install-on-my-server"> ➤ Install on my server</a></li>
    <li><a href="#database"> ➤ Database & data analysis </a></li>
    <li><a href="#credits"> ➤ Credits</a></li>
  </ol>
</details>

![Rainbow line](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="features"> :rocket: Features</h2>

<p align="justify"> 
    • Tracks a wide range of direct and indirect player interactions.<br/>
    • Stores interactions and player information in a SQLite database.<br/>
    • Lightweight performance oriented to limit any lag on the main server thread.<br/>
</p>

![Rainbow line](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="about-aetheris"> :pencil: About Aetheris</h2>
<p align="justify"> 
    Aetheris is a modular Minecraft server plugin that specializes into data collection about player interaction.<br/><br/>
    It logs interactions, such as item pickups or combat triggers, in a database and can handle large volumes of concurrent events.<br/><br/>
    The stored data can be used by admins for various purposes, such as player behavior analysis and server optimization. However, the primary focus of Aetheris is to collect data on social interactions between players, specifically how groups are formed and how individuals interact within those groups.<br/><br/>
    Aetheris <b>listens to interaction events</b> and <b>stores them</b> in its database but <b>does not analyze</b> or display the data itself as we want to keep the focus on <b>data collection</b>.<br/><br/>
    Projects that display or analyze the data are happily encouraged!<br/><br/>
</p>

![Rainbow line](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="install-on-my-server"> :dvd: Install on my server</h2>

<p align="justify"> 
    1. Download the latest release from the <a href="https://github.com/Hugo-CASTELL/aetheris/releases">Releases</a> page.<br/>
    2. Drop the JAR file into your server's <code>/plugins</code> folder.<br/>
    3. Start the server — the database will auto-initialize in <code>/plugins/Aetheris/</code>.<br/>
    4. No additional setup is required! Optionally configure the <code>config.yml</code> at your ease (coming soon).<br/>
</p>

![Rainbow line](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="database"> :floppy_disk: Database & data analysis</h2>

<h4>Database path</h4>
<p align="justify">
    By default, the Aetheris database is named <code>aetheris.db</code> and will be found at <code>/plugins/Aetheris/aetheris.db</code> after the first load of the plugin.<br/>
</p>

<h4>Database schema</h4>
<p align="justify">
    Aetheris uses a relational schema optimized for fast writings and future analytics:<br/>
    • <code>player</code>: Stores UUID, username, and connection count.<br/>
    • <code>interactions</code>: Timestamped logs referencing the type of interaction and an optional context.<br/>
    • <code>interactions_participants</code>: <br/>
    • <code>interaction_types</code>: Enum-like table of all possible interaction categories.<br/><br/>
    The database is written in third normal form (3NF), ensuring minimal redundancy and efficient indexing.<br/><br/>
    INSERT A SCHEMA DIAGRAM HERE<br/>
</p>

<h4>Handling multiple databases (weekly for example)</h4>
<p align="justify">
    While the server is running, do <b>not</b> delete the database file. Aetheris will <b>not</b> automatically create a new one if the current one is deleted. If you want to delete the database file, please <b>stop the server first</b>.<br/><br/>
    However, you would want to handle multiple databases for performance or data segregation by a criteria of your choice.<br/>
    Aetheris only connects to a database or creates a new one if the database file does not exist and does not modify data, it only adds rows.<br/>
    In the config file, you can set the database filename to your preference, changing for another than the default one.<br/> 
    And if a database file already exists, Aetheris will connect to it, update it to the newest database schema, and use it as usual.<br/><br/>
</p>

![Rainbow line](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="credits"> :scroll: Credits</h2>

<p align="justify"> 
    :star: Thanks to <a href="https://github.com/Egxon" target="_blank">Egxon</a> that contributes to the project by providing valuable feedback and suggestions on the data analysis aspect.<br/>
</p>
