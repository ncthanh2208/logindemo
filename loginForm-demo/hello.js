async function hello(){
    let resp = localStorage.getItem('Token');
    let uri = 'http://localhost:9090/hello';

    let h = new Headers();

    h.append('Accept', 'application/json');

    h.append('Authorization', resp);

    let req = new Request(uri, {
        method: 'GET',
        headers: h,
        credentials: 'same-origin',
        mode: 'cors'
    });

    await fetch(req)
        .then(response => {
            // document.getElementById("text").append(response);
            console.log(response);
        })
        .catch(err => console.log('fail ' + err))
}


document.addEventListener('DOMContentLoaded',
    function () {
        p = document.getElementById("logout");
        p.addEventListener('click', Logout);
    }
);

function Logout(){
    localStorage.removeItem('Token');
    window.location.href='http://127.0.0.1:5500/login.html'
}
$(document).ready(hello)