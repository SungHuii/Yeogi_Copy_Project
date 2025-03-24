import "react";
import { Link } from "react-router-dom";

const ProfileNavigationList = ({title, link}) => {
    return (
        <li>
            <Link to={link}>
                {title}
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="20"
                    height="20"
                    viewBox="0 0 20 20"
                    data-v-212f5d8b="">
                    <path
                        fill="current"
                        d="M7.016 14.594 12.02 10 7 5.392 8.402 4c2.816 2.545 4.485 4.076 5.007 4.594a1.978 1.978 0 0 1 0 2.812L8.422 16l-1.406-1.406Z"></path>
                </svg>
            </Link>
        </li>
    );
}

export default ProfileNavigationList;