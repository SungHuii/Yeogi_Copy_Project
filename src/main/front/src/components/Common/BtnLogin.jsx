import "react";

const BtnLogin = ({ onClick }) => {
    return (
        <button type="button" className="btn-login" onClick={onClick}>
            회원가입 / 로그인
        </button>
    );
};

export default BtnLogin;