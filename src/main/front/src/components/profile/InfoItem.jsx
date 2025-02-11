import "react";

const InfoItem = ({ infoTitle, infoContents }) => {

    return (
        <div>
            <p>{ infoTitle }</p>
            <div>
                <input type="text" value={infoContents} />
            </div>
        </div>
    );
}

export default InfoItem;