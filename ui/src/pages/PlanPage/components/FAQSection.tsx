import React from "react";
import FAQItem from "./FAQItem";

interface FAQItemData {
    question: string;
    answer: string;
}

function FAQSection() {
    const faqItems: FAQItemData[] = [
        {
            question: "What kind of power outlets are in Taniti?",
            answer: "Power outlets are the same as in the United States, 120 volts."
        },
        {
            question: "What are the local laws regarding alcohol on the island?",
            answer: "The drinking age on Taniti is 18 years old. Alcohol is not allowed to be served or sold between the hours of midnight and 9:00am."
        },
        {
            question: "Do the locals on the island speak English?",
            answer: "Many younger Tanitians speak fluent English. Very little English is spoken in rural areas, especially by the older residents."
        },
        {
            question: "What if I have a medical emergency or need to see a doctor?",
            answer: "If you have a medical emergency you should go to the island's hospital. If you need to see a doctor, but it's not an emergency you can visit one of several clinics located on the island."
        },
        {
            question: "How safe is it on the island?",
            answer: "Violent crime is very rare on Taniti, but as tourism increases, there are more reports of pickpocketing and other petty crimes."
        },
        {
            question: "Are local attractions open during national holidays?",
            answer: "Taniti enjoys a large number of national holidays, and many tourist attractions and restaurants will be closed on holidays, so visitors should plan accordingly."
        },
        {
            question: "What currency does Taniti use?",
            answer: "Taniti uses the U.S. dollar as its currency, but many businesses will also accept euros and yen. Several banks facilitate currency exchange, and many businesses accept major credit cards."
        }
    ];

    return (

        <div className="row ps-5 pt-5 pb-5">
            <div className="d-flex justify-content-center pb-1 align-items-center pt-2">
                <div className="divider"></div>
                    <h2 className="text-uppercase">faqs</h2>
                    <div className="divider ps-5"></div>
            </div>
            <div className="accordion mx-auto" id="accordion" role="tablist" aria-multiselectable="true">
                {faqItems.map((item, index) => (
                    <FAQItem key={index} question={item.question} answer={item.answer}/>
                ))}
            </div>
        </div>

    );
}

export default FAQSection;
