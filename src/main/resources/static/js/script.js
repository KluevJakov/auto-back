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
                window.location.reload();
            } else {
                let loginError = document.getElementById("loginError");
                loginError.innerText = "Ошибка авторизации";
            }
        } catch (error) {
            alert('An error occurred. Please try again.');
        }
    }

    regForm.onsubmit = async function (event) {
        event.preventDefault();
        let regError = document.getElementById("regError");
        const login = document.getElementById('usernameReg').value;
        const password = document.getElementById('passwordReg').value;
        const passwordRepeat = document.getElementById('passwordRepeatReg').value;

        if (password != passwordRepeat) {
            regError.innerText = "Пароли не совпадают";
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ login, password })
            });

            const data = await response.text();

            if (response.ok) {
                alert('Аккаунт успешно создан!');
                modal.style.display = 'none';
                window.location.reload();
            } else {
                regError.innerText = data;
            }
        } catch (error) {
            alert('An error occurred. Please try again.');
        }
    }

    const datas4 = document.getElementsByClassName("t706__cartwin-totalamount-wrap");
    Array.from(datas4).forEach((el) => {
        el.remove();
    });

    let commitAction = document.getElementById("commitAction");
    commitAction.addEventListener("click", function() {
        alert("!");
    });

});
/**
document.getElementById('form756843260').addEventListener('submit', function(event) {
    event.preventDefault();

    // Получаем значения полей формы
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData);

    fetch('http://localhost:8080/order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        if (result.success) {
            document.querySelector('.js-successbox').style.display = 'block';
        } else {
            document.querySelector('.js-errorbox-all').style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.querySelector('.js-errorbox-all').style.display = 'block';
    });
});**/