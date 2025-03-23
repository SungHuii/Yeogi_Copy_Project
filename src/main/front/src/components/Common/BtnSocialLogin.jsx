import 'react';

const BtnSocialLogin = ({title, className}) => {
    return(
        <form>
            <button className={`btn ${className}`}>{title}</button>
        </form>
    )
}

export default BtnSocialLogin;