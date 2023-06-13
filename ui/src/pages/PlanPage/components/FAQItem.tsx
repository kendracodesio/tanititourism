import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleUp } from '@fortawesome/free-solid-svg-icons';

interface FAQItemProps {
    question: string;
    answer: string;
}
function FAQItem({ question, answer }: FAQItemProps) {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <div className="card">
            <div className="card-header" role="tab">
                <div className="d-flex justify-content-between" onClick={() => setIsOpen(!isOpen)}>
                    <div>
                        <p className="mt-1 accordion-heading">{question}</p>
                    </div>
                    <div>
                        <FontAwesomeIcon icon={faAngleUp} className={`icon ${isOpen ? 'open' : '' }`}></FontAwesomeIcon>
                    </div>
                </div>
            </div>
            {isOpen && (
                <div className="collapse show" role="tabpanel">
                    <div className="card-block">
                        <p className="p-4 accordion-body-text">{answer}</p>
                    </div>
                </div>
            )}
        </div>
    );
}
export default FAQItem;