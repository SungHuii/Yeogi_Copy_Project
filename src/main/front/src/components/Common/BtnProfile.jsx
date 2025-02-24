import "react";

const BtnProfile = ({onClick}) => {
    return (
        <button type="button" className="btn-login" onClick={onClick}>
            프로필 페이지로
        </button>
    );
}

export default BtnProfile;