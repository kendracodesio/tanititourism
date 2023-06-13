import {Link} from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowRight } from '@fortawesome/free-solid-svg-icons'

interface InternalLinkProps {
    to: string;
    linkText: string;


}

function InternalLink({to, linkText}: InternalLinkProps) {
    return (
        <div className="internal-link">
            <Link to={to}>{linkText} <FontAwesomeIcon icon={faArrowRight}></FontAwesomeIcon> </Link>
        </div>
    )
}
export default InternalLink;