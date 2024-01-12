interface ErrorProps {
  title: string;
  description: string;
}

const ErrorPage: React.FC<ErrorProps> = ({ title, description }) => {
  return (
    <div className="flex flex-col justify-center align-middle text-white p-4 ">
      <h2>{title}</h2>
      <p>{description}</p>
    </div>
  );
};

export default ErrorPage;
