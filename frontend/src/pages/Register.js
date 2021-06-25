import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';


const Input = styled.input`
    width:100%;
	padding:12px;
	box-sizing:border-box;
	border:1px solid #e5e5e5;
	margin:0;
`;

const Button = styled.div`
    width:100%;
	text-align:Center;
	display: inline-block;
	margin-right: 20px;

	&:hover{
		font-weight: 600;
		color: #38955f;
	}
`;

const Register = () => {
    return (
        <div>
            <Input/>
            <Input/>
            <Input/>
            <Input/>
            <Link to="/join">
                <Button> 확인 </Button>
            </Link>
            <Link to="/">
                <Button> 뒤로가기 </Button>
            </Link>
        </div>
    );
};

export default Register;