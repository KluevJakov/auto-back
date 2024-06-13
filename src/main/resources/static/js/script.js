const datas1 = document.getElementsByClassName("t-tildalabel-free");
Array.from(datas1).forEach((el) => {
    el.remove();
});

const datas2 = document.getElementsByClassName("t706__carticon");
Array.from(datas2).forEach((el) => {
    el.remove();
});

const datas3 = document.getElementsByClassName("t706__carticon_showed");
Array.from(datas3).forEach((el) => {
    el.remove();
});

document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modal');
    const modal2 = document.getElementById('modal2');
    const openModalBtn = document.getElementById('openModalBtn');
    const regModalBtn = document.getElementById('regModalBtn');
    const closeBtn = document.getElementsByClassName('close')[0];
    const closeBtn2 = document.getElementsByClassName('close')[1];
    const loginForm = document.getElementById('loginForm');
    const regForm = document.getElementById('regForm');


    openModalBtn.onclick = function () {
        modal.style.display = 'block';
    }

    regModalBtn.onclick = function () {
        modal2.style.display = 'block';
    }

    closeBtn.onclick = function () {
        modal.style.display = 'none';
    }

    closeBtn2.onclick = function () {
        modal2.style.display = 'none';
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = 'none';
            modal2.style.display = 'none';
        }
    }

    loginForm.onsubmit = async function (event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            let formData = new FormData();
            formData.append('login', username);
            formData.append('password', password);

            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                body: formData
            });

            const data = await response.text();

            if (response.ok) {
                modal.style.display = 'none';
                window.location.href = "/";
            } else {
                window.location.href = "/";
            }
        } catch (error) {
            alert('An error occurred. Please try again.');
        }
    }

    regForm.onsubmit = async function (event) {
        event.preventDefault();

        const login = document.getElementById('usernameReg').value;
        const password = document.getElementById('passwordReg').value;
        const passwordRepeat = document.getElementById('passwordRepeatReg').value;

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ login, password })
            });

            const data = await response.json();

            if (response.ok) {
                alert('Reg successful!');
                modal.style.display = 'none';
            } else {
                alert(`Error: ${data.message}`);
            }
        } catch (error) {
            alert('An error occurred. Please try again.');
        }
    }
});
