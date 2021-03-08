
document.addEventListener('DOMContentLoaded',
    function () {
        p = document.querySelector('p');
        p.addEventListener('click', doFetch);
    }
);

async function doFetch() {
    window.location.href='http://127.0.0.1:5500/hello.html'

}

document.addEventListener('DOMContentLoaded',
    function () {
        login = document.getElementById('submit');
        login.addEventListener('click', doFetch2);
    }
);
async function doFetch2(event) {
    event.preventDefault();
    let valueUsername = document.getElementById('username').value;
    let valuePassword = document.getElementById('password').value;

    let data = {
        username: valueUsername,
        password: valuePassword,
    };
    localStorage.removeItem('Token');
    async function postData(url = '', data = {}) {
        const response = await fetch(url, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        return response.json();
    }

    postData('http://localhost:9090/authenticate', data)
        .then(response => {
            let token1 = 'Bearer' + " " + response.token;
            localStorage.setItem('Token', token1);
            doFetch()
        })
}



