
$.get(
    "https://api.coinmarketcap.com/v1/ticker/?limit=10",

    // Callback

    function(data) {
        $("#result").html(
            data.map(c => c.name).join("<br>"))
    }
);


public get<T>(endpoint: string): Observable<T> {
    return this.httpClient.get( endpoint );
}

...

get<CryptoCoin[]> ("https://api.coinmarketcap.com/v1/ticker/?limit=10")
    .subscribe(
        coin => console.log(coin.name)
    );