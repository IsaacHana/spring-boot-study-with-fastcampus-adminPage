import { RingLoader } from "react-spinners";

const Loader = () => {
  return (
    <div
      className="
                h-[70vh]
                flex
                flex-col
                justify-center
                items-center
            "
    >
      <RingLoader size={100} color="blue" />
    </div>
  );
};

export default Loader;
