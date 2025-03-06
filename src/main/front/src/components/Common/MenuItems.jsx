import 'react';

// sihong 20250210 - 메뉴 컴포넌트 추가
const MenuItems = ({ menuName }) => {
    return (
        <li>
            <a>
                {menuName}
                <svg
                    width="20"
                    height="20"
                    viewBox="0 0 20 20"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M7.016 14.594L12.02 10 7 5.392 8.402 4c2.816 2.545 4.485 4.076 5.007 4.594a1.978 1.978 0 010 2.812L8.422 16l-1.406-1.406z"
                        fill="current"></path>
                </svg>
            </a>
        </li>
    );
};

export default MenuItems;
