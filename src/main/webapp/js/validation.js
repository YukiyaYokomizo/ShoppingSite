document.addEventListener("DOMContentLoaded", function () {

	const form = document.getElementById("user-form");

	if (form == null) {
		return;
	}

	const id = document.getElementById("id");
	const lastname = document.getElementById("lastname");
	const firstname = document.getElementById("firstname");
	const password = document.getElementById("password");
	const passwordConfirm = document.getElementById("passwordConfirm");
	const address = document.getElementById("address");
	const mailaddress = document.getElementById("mailaddress");

	// パスワード入力中のリアルタイムチェック
	password.addEventListener("input", function () {
		validatePassword(false);

		// 確認用パスワードが入力済みの場合だけ、一致チェックする
		if (passwordConfirm != null && passwordConfirm.value.trim() !== "") {
			validatePasswordConfirm(false);
		}
	});

	// 確認用パスワード入力中のリアルタイムチェック
	if (passwordConfirm != null) {
		passwordConfirm.addEventListener("input", function () {
			validatePasswordConfirm(false);
		});
	}

	// メールアドレス入力中のリアルタイムチェック
	mailaddress.addEventListener("input", function () {
		validateMailAddress(false);
	});

	// 確認へボタンを押したときのチェック
	form.addEventListener("submit", function (event) {

		let isValid = true;

		clearErrors();

		if (!validateRequired(id, "id-error", "IDを入力してください。")) {
			isValid = false;
		}

		if (!validateRequired(lastname, "lastname-error", "姓を入力してください。")) {
			isValid = false;
		}

		if (!validateRequired(firstname, "firstname-error", "名を入力してください。")) {
			isValid = false;
		}

		if (!validatePassword(true)) {
			isValid = false;
		}

		if (!validatePasswordConfirm(true)) {
			isValid = false;
		}

		if (!validateRequired(address, "address-error", "住所を入力してください。")) {
			isValid = false;
		}

		if (!validateMailAddress(true)) {
			isValid = false;
		}

		if (!isValid) {
			event.preventDefault();
		}
	});
});


function validateRequired(inputElement, errorId, message) {

	if (inputElement.value.trim() === "") {
		showError(inputElement, errorId, message);
		return false;
	}

	return true;
}


function validatePassword(isSubmit) {

	const password = document.getElementById("password");

	clearOneError(password, "password-error");

	if (password.value.trim() === "") {
		if (isSubmit) {
			showError(password, "password-error", "パスワードを入力してください。");
			return false;
		}

		return true;
	}

	if (password.value.length < 4 || password.value.length > 20) {
		showError(password, "password-error", "パスワードは4文字以上20文字以下で入力してください。");
		return false;
	}

	return true;
}


function validatePasswordConfirm(isSubmit) {

	const password = document.getElementById("password");
	const passwordConfirm = document.getElementById("passwordConfirm");

	if (passwordConfirm == null) {
		return true;
	}

	clearOneError(passwordConfirm, "passwordConfirm-error");

	// 入力中は、確認用パスワードが空ならまだエラーを出さない
	if (!isSubmit && passwordConfirm.value.trim() === "") {
		return true;
	}

	// 送信時は、確認用パスワードが空ならエラーを出す
	if (isSubmit && passwordConfirm.value.trim() === "") {
		showError(passwordConfirm, "passwordConfirm-error", "確認用パスワードを入力してください。");
		return false;
	}

	if (password.value !== passwordConfirm.value) {
		showError(passwordConfirm, "passwordConfirm-error", "パスワードが一致しません。");
		return false;
	}

	return true;
}


function validateMailAddress(isSubmit) {

	const mailaddress = document.getElementById("mailaddress");

	clearOneError(mailaddress, "mailaddress-error");

	// 入力中は、空欄ならまだエラーを出さない
	if (!isSubmit && mailaddress.value.trim() === "") {
		return true;
	}

	// 送信時は、空欄ならエラーを出す
	if (isSubmit && mailaddress.value.trim() === "") {
		showError(mailaddress, "mailaddress-error", "メールアドレスを入力してください。");
		return false;
	}

	const emailPattern = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

	if (!emailPattern.test(mailaddress.value)) {
		showError(mailaddress, "mailaddress-error", "メールアドレスの形式が正しくありません。");
		return false;
	}

	return true;
}


function showError(inputElement, errorId, message) {

	inputElement.classList.add("input-error");

	const errorElement = document.getElementById(errorId);

	if (errorElement != null) {
		errorElement.textContent = message;
	}
}


function clearOneError(inputElement, errorId) {

	if (inputElement != null) {
		inputElement.classList.remove("input-error");
	}

	const errorElement = document.getElementById(errorId);

	if (errorElement != null) {
		errorElement.textContent = "";
	}
}


function clearErrors() {

	const errors = document.querySelectorAll(".js-error");

	errors.forEach(function (error) {
		error.textContent = "";
	});

	const inputs = document.querySelectorAll("#user-form input");

	inputs.forEach(function (input) {
		input.classList.remove("input-error");
	});
}